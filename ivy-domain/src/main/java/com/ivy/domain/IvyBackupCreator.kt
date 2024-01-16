package com.ivy.domain


interface IvyBackupCreator {
    suspend fun createBackupJson(): String
}