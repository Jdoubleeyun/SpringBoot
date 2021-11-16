# intellij_ultimate_week04-1

|기능|Method|Url|Return|
|------|------|------|-----|
|메모생성하기|POST|/api/memos|Memo|
|메모조회하기|GET|/api/memos|List<Memo>|
|메모변경하기|PUT|/api/memos/{id}|Long|
|메모삭제하기|DELETE|/api/memos/{id}|Long|

<h4>Controller</h4>  
final MemoRepository

final MemoService 
  
Post > memoRepository.save  
  
Get < findAllByModifiedAtBetweenOrderByModifiedAtDesc  
                                                     
Put > update()  
  
Delete > memoRepository.deleteById()  
  

<h4>Service</h4>  
final MemoRepository  
  
update(id, requestDto)  
  

<h4>RequestDto</h4>
MemoRequestDto()  
  
private string username  
  
private string contents  

<h4>memo extends Timestamped</h4>  
id, username, contents  
  
Memo(requestDto) > username, contents  
  
update(requestDto) > username, contents  
  
<h4>Repository(interface) extends JpaRepository</h4>  
findAllByModifiedAtBetweenOrderByModifiedAtDesc  
