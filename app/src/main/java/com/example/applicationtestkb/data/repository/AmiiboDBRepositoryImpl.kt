package com.example.applicationtestkb.data.repository

import com.example.applicationtestkb.data.database.AmiiboCharacterEntity
import com.example.applicationtestkb.data.database.AmiiboDao
import com.example.applicationtestkb.data.model.BaseResponse
import com.example.applicationtestkb.data.model.CharacterAmiibo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AmiiboDBRepositoryImpl
@Inject constructor(
    private val amiiboDao: AmiiboDao
) {
    suspend fun getCharacters(): BaseResponse<CharacterAmiibo> {
        val charactersEntities = amiiboDao.getCharacters()
        val charactersAmiibo = charactersEntities.map {
            CharacterAmiibo(
                amiiboSeries = it.amiiboSeries,
                character = it.character,
                gameSeries = it.gameSeries,
                head = it.head,
                image = it.image,
                name = it.name,
                tail = it.tail,
                type = it.type
            )
        }
        return BaseResponse(charactersAmiibo)
    }

    suspend fun addEverything(characterList: List<CharacterAmiibo>) {
        characterList.forEach {
            add(it)
        }
    }

    suspend fun add(characterModel: CharacterAmiibo) {
        amiiboDao.addCharacter(characterModel.toData())
    }

    suspend fun deleteAllCharacters() {
        amiiboDao.deleteAllCharacters()
    }
}

fun CharacterAmiibo.toData(): AmiiboCharacterEntity {
    return AmiiboCharacterEntity(
        id = 0,
        amiiboSeries = this.amiiboSeries,
        character = this.character,
        gameSeries = this.gameSeries,
        head = this.head,
        image = this.image,
        name = this.name,
        tail = this.tail,
        type = this.type
    )
}