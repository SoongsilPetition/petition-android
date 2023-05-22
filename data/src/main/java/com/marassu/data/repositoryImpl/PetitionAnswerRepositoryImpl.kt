package com.marassu.data.repositoryImpl

import com.marassu.data.source.PetitionAnswerRemoteDataSource
import com.marassu.domain.repository.PetitionAnswerRepository
import com.marassu.entity.petition_answer.PetitionAnswer
import com.marassu.entity.petition_answer.PetitionAnswerRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PetitionAnswerRepositoryImpl @Inject constructor(
    private val petitionAnswerRemoteDataSource: PetitionAnswerRemoteDataSource
): PetitionAnswerRepository {
    override suspend fun postPetitionAnswer(petitionAnswerRequest: PetitionAnswerRequest): Flow<PetitionAnswer> {
        return flow {
            petitionAnswerRemoteDataSource.postPetitionAnswer(petitionAnswerRequest).body()?.let {
                emit(it)
            }
        }
    }

    override suspend fun getPetitionAnswer(petitionId: Int): Flow<PetitionAnswer> {
        return flow {
            petitionAnswerRemoteDataSource.getPetitionAnswer(petitionId).body()?.let {
                emit(it)
            }
        }
    }

    override suspend fun deletePetitionAnswer(petitionId: Int): Flow<Unit> {
        return flow {
            petitionAnswerRemoteDataSource.deletePetitionAnswer(petitionId).body()?.let {
                emit(it)
            }
        }
    }

    override suspend fun modifyPetitionAnswer(
        petitionId: Int,
        petitionAnswerRequest: PetitionAnswerRequest
    ): Flow<PetitionAnswer> {
        return flow {
            petitionAnswerRemoteDataSource.modifyPetitionAnswer(petitionId, petitionAnswerRequest).body()?.let {
                emit(it)
            }
        }
    }

}