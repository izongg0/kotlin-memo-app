# kotlin-memo-app
코틀린을 이용하여 메모장 어플 만들기 
### Jetpack : Room, ViewBinding, Coroutine, Navigation, SafeArgs, ViewModel, ViewModelScope, LiveData 사용

### [Activity or Fragment로 이동](https://github.com/izongg0/kotlin-memo-app/tree/main/memo_Finish/app/src/main/java/com/example/memo_2)
### [Layout으로 이동](https://github.com/izongg0/kotlin-memo-app/tree/main/memo_Finish/app/src/main/res/layout)
<br>
<br>

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
쓰레드를 잘 활용한게 맞는건지 잘 모르겠다.<br> 
이제 한 화면이 아닌, Fragment를 만들어 Fragment에서 입력한 데이터가 MainActivity의 RecyclerView에 들어가도록 구현해보려고 한다.

## 5차
Activity - Fragment 간의 연결이 아닌 Fragment만을 이용한 Navigation을 사용하였다.<br>
EditFragment에서 입력한 데이터가 MainFragment의 RecyclerView에 들어가도록 구현하였다.<br>
이제 MainFragment에 있는 RecyclerView의 아이템을 클릭할 시에 그 메모의 내용을 다른 Fragment에서 보여주도록 <br>
RecyclerView Adapter에서 ClickEvent를 구현해보도록 하겠다.

## 6차

RecyclerView는 ClickEvent를 가지고 있지 않아서 따로 만들어주어야 한다. <br>
만들었는데 잘 작동되지 않는다. 그래서 2차때 했던 ViewModel 과 LiveData를 사용하지 않았을 때의 프로젝트 파일에 <br>
적용 해보았는데 잘 작동한다. 그래서 문제는 ViewModel 과 LiveData를 사용한 RecyclerView에 ClickEvent를 적용하는데에서 문제가 있는 것 같다.<br>
검색을 해보아도 예제가 잘 보이지않아서 혼자 해봐야 할 것같다. 

## 7차

6차 ClickEvent의 이슈를 해결하려고 이틀을 썼지만 해결할 수 없었다. <br>
그래서 ViewModel과 LiveData를 포기하고 ClickEvent를 구현하였다. <br>
다행히 화면 전환간 데이터 관련 에러는 발생하지 않았다. <br>
ClickEvent를 구현하여 메모 리스트에 있는 항목을 하나 클릭하면 그 메모의 내용이 보이는 Fragment로 넘어가도록 구현하였다.<br>
Fragment간의 데이터 전달은 safeArgs를 사용하였다.<br>
<br>
이젠 메모 수정과 삭제기능을 구현하여야 한다. 여기서도 꽤 많은 시간이 소요될거같다.<br>
이것만 끝내면 이번 프로젝트는 마무리가 될 것 같다.

## 8차 - Finish

메모의 수정과 삭제기능을 구현하였다. <br>
DB Query문만 적용하면 바로 구현되는 것이라 생각보다 금방 하였다.<br>
마지막으로 코드정리 하고, Click Effect, Nav Animation 를 적용하고 , 폰트도 적용하였다.<br>
그리고 Fragment Stack관리를 하여 메인화면에서 앱이 원할하게 종료되도록 하였다.<br>
<br>
ViewModel 과 LiveData를 실제 적용하지는 못했지만 시행착오를 겪으면서 어떻게 활용하는지는 알 수 있었다.<br>
간단한 메모장이었지만 배운 Jetpack을 실제 앱을 만드는데 활용할 수 있게 되었다. <br>

## 9차 - Real Finish

8차에서 완료된 것에 ViewModel 과 LiveData를 추가하였다. <br>
ViewModel과 LiveData를 적용했을 때 ClickEvent가 작동하지 않았던 이유가  RecyclerView Adapter의 notifydatasetchanged() 때문이었다.<br>
notifydatasetchanged가 실행되면서 adapter에 적용되어있던 ClickEvent까지 초기화가 되었다. <br>
ClickEvent까지 같이 동기화가 될 줄 알았는데 되지 않았다. <br>
그래서 Adapter 에 연결된 메모 데이터의 전체 동기화가 아닌 변경된 메모데이터만 동기화되도록 DiffUtil을 사용하였다.<br>
변경된 내용을 주고받는 submitList메서드를 사용하기위해 RecyclerView Adapter 을 ListAdapter로 변경하였고, <br>
Adapter 내부에 DiffUtil을 구현하여 사용하였다. <br>
결과적으로 모든 기능들이 정상작동 하였고, <br>
처음 앱을 만들기 전 사용하고자했던 모든 Jetpack 기능들을 사용하여 메모앱을 완성하였다.
