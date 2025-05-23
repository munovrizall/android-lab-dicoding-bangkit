package com.example.codelablazylist.data

import com.example.codelablazylist.model.Hero
import com.example.codelablazylist.model.HeroesData

class HeroRepository {
    fun getHeroes(): List<Hero> {
        return HeroesData.heroes
    }

    fun searchHeroes(query: String): List<Hero>{
        return HeroesData.heroes.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}