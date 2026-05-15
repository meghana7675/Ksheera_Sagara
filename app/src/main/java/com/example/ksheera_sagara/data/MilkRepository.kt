package com.example.ksheerasagara.data

object MilkRepository {

    fun getMilkData(): List<CowMilk> {
        return listOf(
            CowMilk("Cow A", 12f),
            CowMilk("Cow B", 8f),
            CowMilk("Cow C", 15f),
            CowMilk("Cow D", 5f),
            CowMilk("Cow E", 10f)
        )
    }
}