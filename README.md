FragmentEX

---
1. <a href = "#content1">FragmentManager</a></br>
2. <a href = "#content2">Fragment 를 액티비티 layout 파일의 계층 구조로 정의하는 방법*</a></br>
3. <a href = "#content3">FragmentTransaction</a></br>
4. <a href = "#content4">프래그먼트로 데이터 전달하기</a></br>
* <a href = "#ref">참고링크</a>
---
><a id = "content1">**1. FragmentManager**</a></br>

-FragmentManager 를 통해 FragmentTransaction 생성 및 실행</br>

1 Activity 에서 FragmentManager 에 접근</br>
-supportFragmentManager 프로퍼티 이용</br>
`val transaction = supportFragmentManager.beginTransaction()`</br>
cf. findFragmentById()를 사용하여 현재 프래그먼트 참조 얻을 수 있음</br>
`val fragment: ExampleFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as ExampleFragment`</br>

2 Fragment 에서 FragmentManager 에 접근</br>
-childFragmentManager 프로퍼티 / parentFragmentManager 프로퍼티 이용</br>
`parentFragmentManager.beginTransaction()`

<br></br>
<br></br>

><a id = "content2">**2. Fragment 를 액티비티 layout 파일의 계층 구조로 정의하는 방법**</a></br>

-액티비티의 레이아웃 계층구조 안에서 프래그먼트를 배치할 위치를 정의하는 FragmentContainerView 를 추가한 후(1 or 2)</br>

1 액티비티의 레이아웃 파일안에서 프래그먼트를 직접 정의</br>
```xml
<!--android:name(class 속성도 가능) 속성으로 인스턴스화 하려는 프래그먼트 클래스의 이름을 정의-->

<androidx.fragment.app.FragmentContainerView
    android:id="@+id/fragmentContainerView"
    android:name="com.example.fragmentex.fragment.DetailFragment"
    android:layout_width="0dp"
    android:layout_height="0dp"
    android:layout_marginTop="16dp"
    android:background="#35000000"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/textView2"/>
```


2 액티비티 코드 안에서 프래그먼트를 add, replace, remove</br>
-액티비티가 실행되는 동안, 프래그먼트를 add, remove, replace 할 수 있음</br>
-프래그먼트를 추가/삭제/교체하는 역할인 FragmentTransaction을 만드는데 사용하는 FragmentManager 의 인스턴스를 얻어야 함</br>

```kotlin
//FragmentManager 를 통해 FragmentTransaction 획득
val transaction = supportFragmentManager.beginTransaction()

//add를 통해 container 에 Fragment 추가
transaction.add(R.id.fragmentContainerView, listFragment)

//transaction 과 관련된 프래그먼트의 상태 변경을 최적화하여 애니메이션과 전환이 올바르게 작동하도록 함
transaction.setReorderingAllowed(true)

//commit 을 통해 transaction 등록
transaction.commit()
```

cf.공식문서에서 FrameLayout 말고 FragmentContainerView 사용을 강력하게 권장</br>
FragmentContainerView 는 FrameLayout 을 상속받아 Fragment Transaction 을 안정적으로 처리할 수 있고, Fragment 의 동작을 조정할 수 있는 추가 기능이 있음</br>


<br></br>
<br></br>


><a id = "content3">**3. FragmentTransaction**</a></br>

`val fragmentManager = supportFragmentManager`</br>
`val fragmentTransactio = fragmentManager.beginTransaction()`</br>
-트랜잭션 : 여러 개의 의존성이 있는 동작을 한 번에 실행할 때 중간에 하나라도 잘못되면 모든 동작을 복구하는 하나의 작업 단위</br>
(프래그먼트를 삽입/삭제하는 도중에 문제가 발생하면 원래 상태로 되돌리기 위해 트랜잭션이라는 개념을 사용)</br>
-Fragment 를 add, remove, replace 기능 제공</br>
-다수의 프래그먼트를 추가하고 변경하는 액션을 하나의 트랜잭션으로 묶을 수 있음</br>
-FragmentTransaction은 마지막에 트랜잭션에 대해 반드시 commit() 을 호출해야 함</br>

1 commit()</br>
-FragmentManager 에게 트랜잭션에 모든 명령이 추가되었다고 알림</br>
-비동기적이고 트랜잭션을 즉시 수행하지 않음</br>
(1)commit()호출 시점에서 트랜잭션은 메인 스레드에 예약됨</br>
-> (2)메인 스레드에서 예약된 트랙잭션의 수행이 가능할 때 트랜잭션이 수행됨</br>
```kotlin
fragmentManager.commit {
    setReorderingAllowed(true)
    add(R.id.fragment_container, FirstFragment())
}
```
2 commitNow()</br>
-트랜젝션을 즉시 수행. 단, addToBackStack 과 호환되지 않음</br>
```kotlin
fragmentManager.commitNow {
    setReorderingAllowed(true)
    add(R.id.fragment_container, FirstFragment())
}
```

3 addToBackStack()</br>
-add() 와 commit() 사이에 addToBackStack()을 추가하면 스마트폰의 뒤로가기 버튼 사용 가능</br>
cf. addToBackStack() 을 사용하지 않았는데 뒤로가기를 하면 액티비티가 종료됨</br>
-트랙잭션을 하나의 액티비티처럼 백스택에 담아둘 수 있음</br>
-스마트폰의 뒤로가기 버튼으로 트랜잭션 전체를 액티비티처럼 제거할 수 있음</br>

<br></br>
<br></br>

><a id = "content4">**4. 프래그먼트로 데이터 전달하기**</a></br>

1 Fragment Result API
-FragmentManager 는 프래그먼트 결과의 중앙 저장소 역할을 할 수 있음
-경우에 따라 두 프래그먼트 간에 또는 프래그먼트와 호스트 활동 간에 일회성 값을 전달 경우
ex) QR 코드를 읽고 이전 프래그먼트로 데이터를 다시 전달하는 프래그먼트
-Fragment 버전 1.3.0 이상에서 각 FragmentManager는 FragmentResultOwner를 구현
-이번 변경으로 구성요소가 서로를 직접 참조하지 않아도 프래그먼트 결과를 설정하고 이러한 결과를 수신 대기하여 구성요소가 서로 통신할 수 있음



<br></br>
<br></br>
---

><a id = "ref">**참고링크**</a></br>

FragmentManager</br>
https://velog.io/@changhee09/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-Fragment%EC%99%80-FragmentManager</br>

Fragment Transaction</br>
https://velog.io/@changhee09/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-Fragment-Manager-Fragment-Transaction</br>

프래그먼트와 통신</br>
https://developer.android.com/guide/fragments/communicate</br>

Fragment 안의 Fragment 처리(교체, 백스택, Back 버튼 클릭 등) ― parentFragmentManager vs childFragmentManager</br>
https://ddangeun.tistory.com/m/127</br>