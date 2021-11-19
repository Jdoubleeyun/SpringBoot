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
    review_desc = request.form["review_desc"]       # myReivew 페이지로부터 업데이트 리뷰 내용을 가져옴
    review_name = request.form["review_name"]       # myReivew 페이지로부터 업데이트할 식당 이름을 가져옴
    db.restaurant_review.update_one({'shop': review_name}, {'$set': {'reviews': review_desc}})      # 해당 내용 리뷰 db에서 업데이트
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