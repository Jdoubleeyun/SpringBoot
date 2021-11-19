# W3_Personal

B SearchController (GET)  
List<ItemDto> getItems(@RequestParam String query) >  naverShopSearch.fromJSONtoItems(resultString);  
query를 받아서 NavershopSearch.search{해당 query의 네이버 정보 get}, NavershopSearch.fromJSONtoItems{정보 변수별로 추림} > itemDtoList > ItemDto(title, link, image, lprice)

A MemoController (GET) > memoRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(start, end) 
MemoRepository extends JpaRepository<Memo, Long>  
B ProductRestController (GET) > productRepository.findAll()  
productRepository extends JpaRepository<Product, Long>    

A MemoController (POST) requestDto > memo 
MemoRequestDto requestDto 가 들어오면 Memo(requestDto) > memoRepository에 save  
B ProductRestController (POST) requestDto > product  
ProductRequestDto requestDto 가 들어오면 product(requestDto) > productRepository에 save
  
B ProductRestController (PUT) 
id, requestDto > productService.update(id, requestDto) > product.update(requestDto) > this.myprice = requestDto.getMyprice()


# W3_Personal API
|기능|Method|Url|Return|function|
|------|------|------|-----|------|
|2.게시글 작성페이지 - 제목, 작성자명, 작성 내용을 입력|
|게시글작성 및 생성하기|POST|/api/memos|Memo|writePost()|
|관심 상품 id등록 modal start|POST|/api/products|Product|addProduct(itemDto)|
|글쓰기 완료 버튼|
|1.전체 게시글 목록 조회페이지 - 작성 날짜 기준으로 내림차순/ 제목, 작성자명, 작성 날짜를 조회|
|검색어에 대한 정보 생성하기|GET|/api/search @RequestParam|List<ItemDto>|execSearch(), addHTML(itemDto)|
|전체게시글 목록 조회하기|GET|/api/memos|List<Memo>|getMessages(), addHTML(id, username, contents, modifiedAt)|
|특정 게시글 버튼|
|3. 게시글 조회페이지 - 제목, 작성자명, 작성 날짜, 작성 내용을 조회|
|관심 상품 목록 조회|GET|/api/products|List<Product>|showProduct(), addProductItem(product)|
|전체게시글 목록 조회하기|GET|/api/memos|List<Memo>|getMessages(), addHTML(id, username, contents, modifiedAt)|
