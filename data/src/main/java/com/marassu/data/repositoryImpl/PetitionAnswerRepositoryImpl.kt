package com.marassu.data.repositoryImpl

import com.marassu.data.source.PetitionAnswerRemoteDataSource
import com.marassu.data.util.CommonAPILogic
import com.marassu.domain.repository.PetitionAnswerRepository
import com.marassu.entity.petition_answer.PetitionAnswer
import com.marassu.entity.petition_answer.PetitionAnswerRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PetitionAnswerRepositoryImpl @Inject constructor(
    private val petitionAnswerRemoteDataSource: PetitionAnswerRemoteDataSource
): PetitionAnswerRepository {
    override suspend fun postPetitionAnswer(petitionAnswerRequest: PetitionAnswerRequest): Flow<PetitionAnswer> {
        return CommonAPILogic.checkError(petitionAnswerRemoteDataSource.postPetitionAnswer(petitionAnswerRequest))
    }

    override suspend fun getPetitionAnswer(petitionId: Long): Flow<PetitionAnswer> {
        return CommonAPILogic.checkError(petitionAnswerRemoteDataSource.getPetitionAnswer(petitionId))
    }

    override suspend fun deletePetitionAnswer(petitionId: Int): Flow<Unit> {
        return CommonAPILogic.checkError(petitionAnswerRemoteDataSource.deletePetitionAnswer(petitionId))
    }

    override suspend fun modifyPetitionAnswer(
        petitionId: Int,
        petitionAnswerRequest: PetitionAnswerRequest
    ): Flow<PetitionAnswer> {
        return CommonAPILogic.checkError(petitionAnswerRemoteDataSource.modifyPetitionAnswer(petitionId, petitionAnswerRequest))
    }

}