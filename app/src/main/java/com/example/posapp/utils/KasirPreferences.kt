package com.example.posapp.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

class KasirPreferences(
    private val store: DataStore<Preferences>
) {

    private val KASIR_NAME_DATA = stringPreferencesKey("KASIR_NAMA")
    private val KASIR_PHONE_NUMBER = intPreferencesKey("KASIR_NUMBER")
    suspend fun saveKasir(name: String, phoneNumber: Int) {
        store.edit {
            it[KASIR_NAME_DATA] = name
            it[KASIR_PHONE_NUMBER] = phoneNumber
        }
    }

    suspend fun getKasirValue(): Pair<String, Int>? {
        val res = store.data.first()
        return Pair(res[KASIR_NAME_DATA] ?: return null, res[KASIR_PHONE_NUMBER] ?: return null)
    }
}