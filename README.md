# intellij_ultimate_week04-1

1. SearchController (GET)  
List<ItemDto> getItems(@RequestParam String query) >  naverShopSearch.fromJSONtoItems(resultString);  
query를 받아서 NavershopSearch.search{해당 query의 네이버 정보 get}, NavershopSearch.fromJSONtoItems{정보 변수별로 추림} > itemDtoList > ItemDto(title, link, image, lprice)
  
2. ProductRestController (GET) > productRepository.findAll()  
productRepository extends JpaRepository<Product, Long>  

2. ProductRestController (POST) requestDto > product  
ProductRequestDto requestDto 가 들어오면 product(requestDto) > productRepository에 save
  
2. ProductRestController (PUT) 
id, requestDto > productService.update(id, requestDto) > product.update(requestDto) > this.myprice = requestDto.getMyprice()


|기능|Method|Url|Return|function|
|------|------|------|-----|------|
|검색어에 대한 정보 생성하기|GET|/api/search @RequestParam|List<ItemDto>|execSearch(), addHTML(itemDto)|
|관심 상품 목록 조회|GET|/api/products|List<Product>|showProduct(), addProductItem(product)|
|관심 상품 id등록 modal start|POST|/api/products|Product|addProduct(itemDto)|
|설정 가격 변경 modal end|PUT|/api/products/{id} or ${targetId}|Long|setMyprice()|

