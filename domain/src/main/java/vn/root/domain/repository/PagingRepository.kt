package vn.root.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import vn.root.domain.model.ItemModel

interface PagingRepository {

     fun getPagingNetwork(): Flow<PagingData<ItemModel>>

     fun getPagingLocal(): Flow<PagingData<ItemModel>>
}
