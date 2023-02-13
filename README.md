# kotlin-memo-app
코틀린을 이용하여 메모장 어플 만들기 

![memoframe](https://user-images.githubusercontent.com/108528803/218311775-c5610f11-5dcb-4cc2-be1b-d5af245e8f76.jpg)

----
## 1차 시도 .. Fail

첫 프로젝트부터 Room, Navigation, Fragment, ViewModel, LiveData, ViewBinding <br>모든걸 다 쓰려고 욕심내다보니 점점 산으로 가서 실패로 결론.<br> 다음시도엔 Room, Fragment, ViewBinding 만 사용하여 진행하도록 한다.

----
## 2차 시도

Fragment도 사용하지 않고 Room, ViewBinding, Coroutine을 사용하여 진행하여 한 페이지 내부에서 메모 기능이 작동하도록 구현 성공하였다.

----
## 3차 시도

Room, ViewBinding, ViewModel, LiveData, ViewModelScope 를 사용하여 구현하려 했으나
한번 insert 한번 하면 엄청난 양의 중복된 데이터가 화면에 나타나는 현상이 발생하였다.
RecyclerView에 데이터를 불러올 때 LiveData가 잘 동기화 되지 않고 <br>
Insert하는 순간 하나의 데이터가 쌓여서 들어가는(?) 거 같다. <br>
RecyclerView에 데이터를 불러올 때 LiveData를 초기화하고 불러오는 과정이 필요할거 같다.

