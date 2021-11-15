# intellij_ultimate_week03

|기능|Method|Url|Return|
|------|------|------|-----|
|메모생성하기|POST|/api/memos|Memo|
|메모조회하기|GET|/api/memos|List<Memo>|
|메모변경하기|PUT|/api/memos/{id}|Long|
|메모삭제하기|DELETE|/api/memos/{id}|Long|

Controller
final MemoRepository
final MemoService
Post > memoRepository.save
Get < findAllByModifiedAtBetweenOrderByModifiedAtDesc
Put > update()
Delete > memoRepository.deleteById()

Service
final MemoRepository
update(id, requestDto)

RequestDto
MemoRequestDto()
private string username
private string contents

memo extends Timestamped
id, username, contents
Memo(requestDto) > username, contents
update(requestDto) > username, contents
  
Repository(interface) extends JpaRepository
findAllByModifiedAtBetweenOrderByModifiedAtDesc


