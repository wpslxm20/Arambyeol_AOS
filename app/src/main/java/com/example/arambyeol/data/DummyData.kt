//package com.example.arambyeol.data
//
//import com.example.arambyeol.service.RetrofitObj
//import retrofit2.Callback
//import retrofit2.Retrofit
//
//object DummyData {
//    val dummyMealPlan = MealPlan(
//        today = DayPlan(
//            morning = listOf(
//                Course(
//                    courseName = "A코스/한식",
//                    menu = listOf(
//                        "쌀밥 / 누룽지",
//                        "수제비국",
//                        "돈육장조림",
//                        "콩나물무침",
//                        "양반김",
//                        "배추김치",
//                        "모듬음료"
//                    )
//                ),
//                Course(
//                    courseName = "테이크아웃",
//                    menu = listOf(
//                        "미니꿀호떡",
//                        "견과류",
//                        "모듬음료"
//                    )
//                )
//            ),
//            lunch = listOf(
//                Course(
//                    courseName = "A코스/한식",
//                    menu = listOf(
//                        "쌀밥",
//                        "소고기무국",
//                        "크림미트볼조림",
//                        "우엉조림",
//                        "청경채겉절이",
//                        "배추김치",
//                        "오렌지"
//                    )
//                )
//            ),
//            dinner = listOf(
//                Course(
//                    courseName = "A코스/한식",
//                    menu = listOf(
//                        "참치김치덮밥",
//                        "파송송계란탕",
//                        "브로콜리맛살볶음",
//                        "무말랭이지",
//                        "깍두기",
//                        "살구주스"
//                    )
//                )
//            )
//        ),
//        tomorrow = DayPlan(
//            morning = listOf(
//                Course(
//                    courseName = "A코스/한식",
//                    menu = listOf(
//                        "쌀밥 / 누룽지",
//                        "사골우거지탕",
//                        "두부계란구이",
//                        "알감자조림",
//                        "부추겉절이",
//                        "배추김치",
//                        "모듬음료"
//                    )
//                )
//            ),
//            lunch = listOf(
//                Course(
//                    courseName = "A코스/한식",
//                    menu = listOf(
//                        "짜장라이스",
//                        "우동장국",
//                        "납작만두*초간장",
//                        "얼갈이겉절이",
//                        "깍두기",
//                        "레몬아이스티"
//                    )
//                )
//            ),
//            dinner = listOf(
//                Course(
//                    courseName = "A코스/한식",
//                    menu = listOf(
//                        "쌀밥",
//                        "돼지김치찌개",
//                        "생선까스*타르S",
//                        "감자채볶음",
//                        "오리엔탈샐러드",
//                        "깍두기",
//                        "매실주스"
//                    )
//                )
//            )
//        ),
//        theDayAfterTomorrow = DayPlan(
//            morning = listOf(
//                Course(
//                    courseName = "A코스/한식",
//                    menu = listOf(
//                        "쌀밥 / 누룽지",
//                        "오징어무국",
//                        "분홍소시지전*케찹",
//                        "청포묵김무침",
//                        "된장고추지",
//                        "배추김치",
//                        "모듬음료"
//                    )
//                )
//            ),
//            lunch = listOf(
//                Course(
//                    courseName = "A코스/한식",
//                    menu = listOf(
//                        "쌀밥",
//                        "시래기국",
//                        "순살닭갈비",
//                        "알어묵조림",
//                        "미역줄기볶음",
//                        "배추김치",
//                        "파인애플주스"
//                    )
//                )
//            ),
//            dinner = listOf(
//                Course(
//                    courseName = "A코스/한식",
//                    menu = listOf(
//                        "쌀밥",
//                        "순두부찌개",
//                        "해물완자조림",
//                        "명엽채볶음",
//                        "열무겉절이",
//                        "배추김치",
//                        "식혜"
//                    )
//                )
//            )
//        )
//    )
//
//
//
//}