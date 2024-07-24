package vn.root.data.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import vn.root.data.model.ItemRaw
import vn.root.data.model.ListResponse
import vn.root.data.model.ObjectResponse
import vn.root.data.model.TokenRaw

interface ApiService {
	
	@POST("/login")
	suspend fun login(): Response<ObjectResponse<TokenRaw>>
	
	@POST("/logout")
	suspend fun logout(): Response<ObjectResponse<Nothing>>
	
	@GET("/paging-page")
	suspend fun getPaging(
		@Query("page") page: Int = 1,
		@Query("lang") lang: String = "en"
	): Response<ListResponse<ItemRaw>>
}
