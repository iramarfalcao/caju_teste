package com.caju.resource

import com.caju.resource.request.TransactionRequest
import com.caju.service.TransactionService
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/cajuapp")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class CajuappController(private val transactionService: TransactionService) {

    @GET
    @Path("/balance/{accountId}")
    fun balance(@PathParam("accountId") accountId: String): Response =
        Response.ok(transactionService.balance(accountId)).build()

    @POST
    @Path("/transaction")
    fun transaction(request: TransactionRequest): Response =
        Response.ok(transactionService.authorizeTransaction(request)).build()
}