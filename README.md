# kotlin-memo-app
코틀린을 이용하여 메모장 어플 만들기 

![memoframe](https://user-images.githubusercontent.com/108528803/218311775-c5610f11-5dcb-4cc2-be1b-d5af245e8f76.jpg)

----
## 1차

첫 프로젝트부터 Room, Navigation, Fragment, ViewModel, LiveData, ViewBinding <br>모든걸 다 쓰려고 욕심내다보니 점점 산으로 가서 실패로 결론.<br> 다음시도엔 Room, Fragment, ViewBinding 만 사용하여 진행하도록 한다.

----
## 2차

Fragment도 사용하지 않고 Room, ViewBinding, Coroutine을 사용하여 진행하여 한 페이지 내부에서 메모 기능이 작동하도록 구현 성공하였다.

----
## 3차

Room, ViewBinding, ViewModel, LiveData, ViewModelScope 를 사용하여 구현하려 했으나
한번 insert 한번 하면 엄청난 양의 중복된 데이터가 화면에 나타나는 현상이 발생하였다.
RecyclerView에 데이터를 불러올 때 LiveData가 잘 동기화 되지 않고 <br>
Insert하는 순간 하나의 데이터가 쌓여서 들어가는(?) 거 같다. <br>
RecyclerView에 데이터를 불러올 때 LiveData를 초기화하고 불러오는 과정이 필요할거 같다.

## 4차

Room, ViewBinding, ViewModel, LiveData, ViewModelScope 를 사용하여 한 페이지 내부에서 메모기능이 작동하도록 구현 성공하였다.<br>
3차시도에서 데이터가 한번에 여러 개 들어가는 것은 LiveData Observe 문제였다. <br>
쓰레드를 잘 활용한게 맞는건지 잘 모르겠지만 이제 한 화면이 아닌 <br>
Fragment를 만들어 Fragment에서 입력한 데이터가 MainActivity의 RecyclerView에 들어가도록 구현해보려고 한다.

## 5차

MainActivity를 사용하지 않고 Navigation으로 Fragment 만으로 구현 성공하였다.
EditFragment에서 입력한 데이터가 MainFragment의 RecyclerView에 들어가도록 구현하였다.
이제 MainFragment에 있는 RecyclerView의 아이템을 클릭할 시에 그 메모의 내용을 다른 Fragment에서 보여주도록 
RecyclerView Adapter에서 ClickEvent를 구현해보도록 하겠다.

