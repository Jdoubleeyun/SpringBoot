from pymongo import MongoClient
from bson import ObjectId # _id를 받아와 사용하기 위한 모듈입니다
#JWT 로그인과 패스워드 암호화 로그인 시간을 위한 모듈입니다.
import jwt
import hashlib

from flask import Flask, render_template, jsonify, request, redirect, url_for
from datetime import datetime, timedelta


app = Flask(__name__)
app.config["TEMPLATES_AUTO_RELOAD"] = True
app.config['UPLOAD_FOLDER'] = "./static/profile_pics"

SECRET_KEY = 'SPARTA'

# client = MongoClient('mongodb://test:test@localhost', 27017)
client = MongoClient('localhost', 27017)
db = client.dbsparta



@app.route('/')
def home():
    token_receive = request.cookies.get('mytoken')
    try:
        payload = jwt.decode(token_receive, SECRET_KEY, algorithms=['HS256'])

        return render_template('login.html')
    except jwt.ExpiredSignatureError:
        return redirect(url_for("login", msg="로그인 시간이 만료되었습니다."))
    except jwt.exceptions.DecodeError:
        return redirect(url_for("login", msg="로그인 정보가 존재하지 않습니다."))


# @app.route('/login')
# def login():
#     msg = request.args.get("msg")
#     return render_template('login.html', msg=msg)



#로그인 부분 api
@app.route('/sign_in', methods=['POST'])
def sign_in():
    # 로그인
    username_receive = request.form['username_give']
    password_receive = request.form['password_give']

    #해시함수
    pw_hash = hashlib.sha256(password_receive.encode('utf-8')).hexdigest()
    result = db.users.find_one({'username': username_receive, 'password': pw_hash})

    #이부분에서 jwt토큰이 발행된다.
    if result is not None:
        payload = {
         'id': username_receive,
            #로그인시간부분
         'exp': datetime.utcnow() + timedelta(seconds=60 * 60 * 24)  # 로그인 24시간 유지
        }
        token = jwt.encode(payload, SECRET_KEY, algorithm='HS256').decode('utf-8')

        return jsonify({'result': 'success', 'token': token})
    # 찾지 못하면
    else:
        return jsonify({'result': 'fail', 'msg': '아이디/비밀번호가 일치하지 않습니다.'})

#회원가입 부분 api
@app.route('/sign_up/save', methods=['POST'])
def sign_up():
    username_receive = request.form['username_give']
    password_receive = request.form['password_give']
    #해시함수
    password_hash = hashlib.sha256(password_receive.encode('utf-8')).hexdigest()
    doc = {
        "username": username_receive,                               # 아이디
        "password": password_hash,                                  # 비밀번호
    }
    db.users.insert_one(doc)
    return jsonify({'result': 'success'})


#아이디 중복확인 서버부분
@app.route('/sign_up/check_dup', methods=['POST'])
def check_dup():
    username_receive = request.form['username_give']
    exists = bool(db.users.find_one({"username": username_receive}))
    return jsonify({'result': 'success', 'exists': exists})


# 선택화면 렌더링
@app.route('/line', methods=['GET'])
def line():
    token_receive = request.cookies.get('mytoken')
    try:
        payload = jwt.decode(token_receive, SECRET_KEY, algorithms=['HS256'])

    except jwt.ExpiredSignatureError:
        return redirect(url_for("login", msg="로그인 시간이 만료되었습니다."))
    except jwt.exceptions.DecodeError:
        return redirect(url_for("login", msg="로그인 정보가 존재하지 않습니다."))

    # 버튼의 역값을 자동으로 받아옵니다.
    a = []
    stations = []
    shop = list(db.restaurant.find({}, {'_id': False}))
    for re in shop:
        a.append(re['place'])
    for re in a:
        if re not in stations:
            stations.append(re)
    return render_template('line_choice.html', stations=stations)


# 홈버튼 클릭시 모든 식당을 리스트업합니다.
@app.route('/shop', methods=['GET'])
def all():
    # break를 쓰기위한 진자구문
    app.jinja_env.add_extension('jinja2.ext.loopcontrols')
    array = []
    stations = []
    shop = list(db.restaurant.find({}, {'_id': False}))
    titles = list(db.restaurant_review.find({}, {"_id": False}))

    for re in shop:
        array.append(re['place'])
    for re in array:
        if re not in stations:
            stations.append(re)
    title = []
    length = len(titles)
    for i in range(length - 1, -1, -1):
        title.append(titles[i])

    for sh in shop:
        allScore = list(db.restaurant_review.find({'shop': sh['name']}))
        point = []
        if not allScore:
            average = 0
        else:
            for score in allScore:
                point.append(score['score'])
                intPoint = list(map(int, point))
                sumPoint = sum(intPoint)
            result = sumPoint / len(point)
            average = f'{result: .1f}'
        db.restaurant.update_one({'name': sh['name']}, {'$set': {'like': average}})

    return render_template('line_base.html', shop=shop, title=title, stations=stations)

# 각 역 클릭시 해당 역 식당 리스트업
# 역버턴 클릭시 해당역의 이름을 키워드로 가져옵니다.
@app.route('/shop/<keyword2>', methods=['GET'])
def line1(keyword2):
    # 로그인 정보가 없으면 반환합니다.
    token_receive = request.cookies.get('mytoken')
    try:
        payload = jwt.decode(token_receive, SECRET_KEY, algorithms=['HS256'])

    except jwt.ExpiredSignatureError:
        return redirect(url_for("login", msg="로그인 시간이 만료되었습니다."))
    except jwt.exceptions.DecodeError:
        return redirect(url_for("login", msg="로그인 정보가 존재하지 않습니다."))

    # 최신리뷰 출력
    app.jinja_env.add_extension('jinja2.ext.loopcontrols')
    array = []
    stations = []
    station = list(db.restaurant.find({}, {'_id': False}))
    titles = list(db.restaurant_review.find({}, {"_id": False}))
    for re in station:
        array.append(re['place'])
    for re in array:
        if re not in stations:
            stations.append(re)
    title = []
    length = len(titles)
    for i in range(length-1, -1, -1):
        title.append(titles[i])

    # db.restaurant에서 키워드값으로 name을 찾고 목록을 역순으로 불러옵니다.
    shop = list(db.restaurant.find({'place': keyword2}).sort('like', -1))

    # 리뷰 점수 업데이트
    # db.restaurant에서 키워드로 검색된 목록을 가져오고 db.restaurant_review에서 shop네임과 같은 목록을 불러옵니다.
    for sh in shop:
        allScore = list(db.restaurant_review.find({'shop': sh['name']}))
        point = []
        # 만약 리뷰가 없다면 평균평점을 0점으로 반환합니다.
        if not allScore:
            average = 0
        # 리뷰가 있다면 point에 리뷰점수를 담습니다.
        else:
            for score in allScore:
                point.append(score['score'])
                # 담은 리뷰점수를 숫자형으로 변환후 리스트로 만들고
                intPoint = list(map(int, point))
                # 리뷰점수를 합산합니다.
                sumPoint = sum(intPoint)
            # 총합산한 리뷰점수를 리뷰의 갯수만큼 나눈뒤
            result = sumPoint / len(point)
            # 소수점 2번째 자리까지 출력합니다.
            average = f'{result: .1f}'
        # 이후 평균값을 다시 db.restaurant에 해당 가게이름의 like값으로 업데이트합니다.
        db.restaurant.update_one({'name': sh['name']}, {'$set': {'like': average}})

    return render_template('line_base.html', shop=shop, title=title, stations=stations)

# 리뷰페이지
@app.route('/review/<keyword>', methods=['GET'])
def review(keyword):
    # db.restaurant에서 키워드값으로 name을 찾고 목록을 역순으로 불러옵니다.
    shop = list(db.restaurant.find({'name': keyword}).sort('like', -1))
    # 리뷰 점수 업데이트
    # db.restaurant에서 키워드로 검색된 목록을 가져오고 db.restaurant_review에서 shop네임과 같은 목록을 불러옵니다.
    for sh in shop:
        allScore = list(db.restaurant_review.find({'shop': sh['name']}))
        point = []
        # 만약 리뷰가 없다면 평균평점을 0점으로 반환합니다.
        if not allScore:
            average = 0
        # 리뷰가 있다면 point에 리뷰점수를 담습니다.
        else:
            for score in allScore:
                point.append(score['score'])
                # 담은 리뷰점수를 숫자형으로 변환후 리스트로 만들고
                intPoint = list(map(int, point))
                # 리뷰점수를 합산합니다.
                sumPoint = sum(intPoint)
            # 총합산한 리뷰점수를 리뷰의 갯수만큼 나눈뒤
            result = sumPoint / len(point)
            # 소수점 2번째 자리까지 출력합니다.
            average = f'{result: .1f}'
        # 이후 평균값을 다시 db.restaurant에 해당 가게이름의 like값으로 업데이트합니다.
        db.restaurant.update_one({'name': sh['name']}, {'$set': {'like': average}})

    # 로그인값이 없다면 반환합니다.
    token_receive = request.cookies.get('mytoken')
    try:
        payload = jwt.decode(token_receive, SECRET_KEY, algorithms=['HS256'])
        user_info = db.users.find_one({"username": payload["id"]})

    except jwt.ExpiredSignatureError:
        return redirect(url_for("login", msg="로그인 시간이 만료되었습니다."))
    except jwt.exceptions.DecodeError:
        return redirect(url_for("login", msg="로그인 정보가 존재하지 않습니다."))

    pic = list(db.restaurant.find({}, {'_id': False}))

    array = []
    stations = []
    station = list(db.restaurant.find({}, {'_id': False}))
    title = list(db.restaurant_review.find({}, {"_id": False}))
    for re in station:
        array.append(re['place'])
    for re in array:
        if re not in stations:
            stations.append(re)
    return render_template('review.html', shop=station, stations=stations, pic=pic, review=title, word=keyword, user_info=user_info)

# 평점0점 초기화
# db.restaurant.update_many({},{'$set':{'like':0}})


# 리뷰작성
@app.route('/api/review_create', methods=['POST'])
def review_create():

    # 리뷰 저장
    shop_receive = request.form["shop_give"]
    score_receive = request.form["score_give"]
    reviews_receive = request.form["reviews_give"]
    date_receive = request.form["date_give"]
    img_receive = request.form["img_give"]
    user_name = request.form["user_give"]

    doc ={
        "reviews": reviews_receive,
        "score": score_receive,
        "shop": shop_receive,
        "date": date_receive,
        "img" : img_receive,
        "user" : user_name
    }
    db.restaurant_review.insert_one(doc)

    shop = list(db.restaurant.find({'place': shop_receive}).sort('like', -1))

    # 리뷰 점수 업데이트
    for sh in shop:
        allScore = list(db.restaurant_review.find({'shop': sh['name']}))
        point = []
        if not allScore:
            average = 0
        else:
            for score in allScore:
                point.append(score['score'])
                intPoint = list(map(int, point))
                sumPoint = sum(intPoint)
            result = sumPoint / len(point)
            average = f'{result: .1f}'
        db.restaurant.update_one({'name': sh['name']}, {'$set': {'like': average}})
    return jsonify({'result': 'success', 'msg': '리뷰가 저장 되었습니다!!'})


# 리뷰 보기
@app.route('/my/reviews', methods=['GET'])
def review_show():
    # 시간 만료시 자동 로그아웃
    token_receive = request.cookies.get('mytoken')                                  # 쿠키에서 토큰을 가져옴
    try:
        payload = jwt.decode(token_receive, SECRET_KEY, algorithms=['HS256'])       # 현 사용자 정보
        user_info = db.users.find_one({"username": payload["id"]})                  # 현 사용자의 아이디를 가져옴
        aa = [user_info]
        my_reviews = list(db.restaurant_review.find({}, {"_id": False}))            # 모든 리뷰 db에 있는 내용을 myReview.html로 전송
        return render_template('myReview.html', review=my_reviews, user=aa)

    except jwt.ExpiredSignatureError:                                               # 로그인 시간 초과
        return redirect(url_for("login", msg="로그인 시간이 만료되었습니다."))
    except jwt.exceptions.DecodeError:                                              # 잘못된 경로로 조회
        return redirect(url_for("login", msg="로그인 정보가 존재하지 않습니다."))
    # /////////////////////////////////////////////////////////////////////

# 리뷰 수정
@app.route('/my/review/modify', methods=['POST'])
def review_modify():
    review_id = request.form['review_id']       # myReivew 페이지로부터 해당 리뷰 아이디 가져옴
    newReview = request.form["newReview"]       # myReivew 페이지로부터 업데이트 리뷰 내용을 가져옴
    db.restaurant_review.update_one({'_id': ObjectId(review_id)}, {'$set': {'reviews': newReview}})      # 해당 내용 리뷰 db에서 업데이트
    return jsonify({'msg': '수정이 완료되었습니다!'})


# 리뷰 제거
@app.route('/my/review/delete', methods=['POST'])
def review_delete():
    review_name = request.form["review_name"]       # myReivew 페이지로부터 삭제할 식당 이름을 가져옴
    review_desc = request.form["review_desc"]       # myReivew 페이지로부터 삭제할 리뷰 내용을 가져옴
    db.restaurant_review.delete_one({'shop': review_name, 'reviews': review_desc})      # 해당 내용을 리뷰 db에서 삭제함

    # 리뷰 점수 업데이트
    allScore = list(db.restaurant_review.find({'shop': review_name}))  # 해당 식당에 해당하는 리뷰 정보 allScore[] 생성
    point = []
    if not allScore:
        average = 0  # 해당 식당에 리뷰가 없으면 평점 0으로 초기화
    else:
        for score in allScore:  # 해당 식당에 리뷰가 있으면 (총 별점의 합/ 리뷰의 개수)으로 평점 average 생성
            point.append(score['score'])
            intPoint = list(map(int, point))
            sumPoint = sum(intPoint)
        result = sumPoint / len(point)
        average = f'{result: .1f}'
    db.restaurant.update_one({'name': review_name}, {'$set': {'like': average}})  # 해당 식당 db에 average 업데이트

    return jsonify({'msg': '삭제가 완료되었습니다!'})

if __name__ == '__main__':
    app.run('0.0.0.0', port=5000, debug=True)