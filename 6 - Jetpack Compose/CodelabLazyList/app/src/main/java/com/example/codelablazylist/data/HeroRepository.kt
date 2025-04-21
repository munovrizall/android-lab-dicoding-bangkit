package com.example.codelablazylist.data

import com.example.codelablazylist.model.Hero
import com.example.codelablazylist.model.HeroesData

class HeroRepository {
    fun getHeroes(): List<Hero> {
        return HeroesData.heroes
    }
}