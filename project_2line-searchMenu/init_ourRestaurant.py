import requests
from bs4 import BeautifulSoup

from pymongo import MongoClient

# 필요한 역 크롤링한 결과 db에 저장

client = MongoClient('mongodb://test:test@localhost', 27017)
db = client.dbsparta

restaurants_list = ['까치산역', '신도림역', '사당역', '강남역', '잠실역', '건대입구역', '성수역',
                    '신설동역', '왕십리역', '신당역', '을지로4가역', '시청역', '홍대입구역', '영등포구청역']


# 역에 따른 검색 결과 url배열 생성 및 해당 역 반환


def get_urls(restaurants_list):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36'}
    data = requests.get('https://www.mangoplate.com/search/' + restaurants_list, headers=headers)
    soup = BeautifulSoup(data.text, 'html.parser')

    urls = []

    for ii in range(1, 6):
        trs = soup.select(
            'body > main > article > div.column-wrapper > div > div > section > '
            'div.search-list-restaurants-inner-wrap > '
            'ul> li:nth-child(' + str(ii) + ') > div ')
        for tr in trs:
            a = tr.select_one('figure > a ')
            if a is not None:
                base_url = 'https://www.mangoplate.com/'
                url = base_url + a['href']
                urls.append(url)
    return urls, restaurants_list


# 객체 생성 및 db저장

def insert_star(url, restaurant):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)AppleWebKit/537.36 (KHTML, like Gecko) '
                      'Chrome/73.0.3683.86 Safari/537.36'}
    data = requests.get(url, headers=headers)
    soup = BeautifulSoup(data.text, 'html.parser')

    img_url = soup.select('body > main > article > aside.restaurant-photos > div> figure >meta')[0]['content']
    name = soup.select_one(
        'body > main > article > div.column-wrapper > div.column-contents > div > section.restaurant-detail > header '
        '> div.restaurant_title_wrap > span > h1').text
    res_address = soup.select_one(
        'body > main > article > div.column-wrapper > div.column-contents > div > section.restaurant-detail > table > '
        'tbody > tr:nth-child(1) > td').text

    # 미사용
    like = soup.select_one(
        'body > main > article > div.column-wrapper > div.column-contents > div > section.restaurant-detail > header > div.status > span.cnt.favorite').text
    if like.split(','):
        like = int(''.join(like.split(',')))
    else:
        like = int(like)

    doc = {
        'name': name,
        'img_url': img_url,
        'place': restaurant,
        'address': res_address,
        'like': int(0)
    }
    db.restaurant.insert_one(doc)
    print(name, " db에 저장완료 했습니다")


# 데이터 db 저장 함수

def insert_all():
    db.restaurant.drop()
    for i in range(0, len(restaurants_list)):
        urls = get_urls(restaurants_list[i])
        for url in urls[0]:
            insert_star(url, urls[1])


insert_all()

