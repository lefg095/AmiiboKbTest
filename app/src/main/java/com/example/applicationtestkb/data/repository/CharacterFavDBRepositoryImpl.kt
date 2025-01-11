package com.example.applicationtestkb.data.repository

import com.example.applicationtestkb.data.database.CharacterFavDao
import com.example.applicationtestkb.data.database.CharacterFavEntity
import com.example.applicationtestkb.data.model.BaseResponse
import com.example.applicationtestkb.data.model.CharacterAmiibo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterFavDBRepositoryImpl
@Inject constructor(
    private val characterFavDao: CharacterFavDao
) : CharacterFavDBRepository {

    override suspend fun getCharactersFav(): BaseResponse<CharacterAmiibo> {
        val charactersFavsEntities = characterFavDao.getCharactersFavorites()
        val charactersFavs = charactersFavsEntities.map {
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
        return BaseResponse(charactersFavs)
    }

    override suspend fun getCharactersFavoritesByTail(tail: String): Int {
        val charactersFavsEntities = characterFavDao.getCharactersFavoritesByTail(tail)
        return charactersFavsEntities.size
    }

    override suspend fun add(characterModel: CharacterAmiibo) : Long {
        return characterFavDao.addCharacterFav(characterModel.favToData())
    }

    override suspend fun delete(characterModel: CharacterAmiibo) : Int {
        return characterFavDao.deleteCharacterFav(characterModel.tail)
    }

}

fun CharacterAmiibo.favToData(): CharacterFavEntity {
    return CharacterFavEntity(
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