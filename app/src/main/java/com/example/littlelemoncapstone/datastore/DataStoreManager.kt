package com.example.littlelemoncapstone.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


private val Context.dataStore by preferencesDataStore(name = "settings")
class DataStoreManager(private val context: Context) {
    companion object {
        private val IS_LOGIN_KEY = booleanPreferencesKey("is_login")
        private  val FIRST_NAME_KEY = stringPreferencesKey("first_name")
        private  val LAST_NAME_KEY = stringPreferencesKey("last_name")
        private  val EMAIL_KEY = stringPreferencesKey("email")

    }
    val isLoginFlow = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[IS_LOGIN_KEY] ?: false
        }
    val firstNameFlow=context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[FIRST_NAME_KEY] ?: ""
        }

    val lastNameFlow=context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
            }.map { preferences ->
            preferences[LAST_NAME_KEY] ?: ""
        }
    val emailFlow = context.dataStore.data
        .catch {
            exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[EMAIL_KEY] ?: ""
        }

    suspend fun setIsLogin(isLogin: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGIN_KEY] = isLogin
        }
    }
    suspend fun setFirstName(firstName: String) {
        context.dataStore.edit { preferences ->
            preferences[FIRST_NAME_KEY] = firstName
        }
    }
    suspend fun setLastName(lastName: String) {
        context.dataStore.edit { preferences ->
            preferences[LAST_NAME_KEY] = lastName
        }
    }
    suspend fun setEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
        }
    }

}