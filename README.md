# Tiki-Test
[![Kotlin](https://img.shields.io/badge/kotlin-1.3.72-brightgreen)](http://kotlinlang.org)
[![Constraint Layout](https://img.shields.io/badge/constraintlayout-2.0.0--beta6-green)](https://developer.android.com/training/constraint-layout)
[![Recyclerview](https://img.shields.io/badge/Recyclerview-1.2.0--alpha03-yellow)](https://developer.android.com/guide/topics/ui/layout/recyclerview)
[![CollapsingToolbarLayout](https://img.shields.io/badge/CollapsingToolbarLayout-1.1.0-red)](https://material.io/)
## 1. Call api Banner + QuickLink parallel <h2>
[![IMAGE ALT TEXT HERE](https://i.ibb.co/fGXkYrC/tiki.png)
## 2. Use Coroutine to call api banner + quick link at the same time <h2>
```kotlin
withContext(IO){
  val getData = launch{     
       .......
  }
  getData.join()
  
  async{
       ........
  }.await()
}
```
## 3. Demo <h2>
[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/qgbeX20IOVQ/0.jpg)](https://www.youtube.com/watch?v=qgbeX20IOVQ)

