package com.caju

import com.caju.containers.PostgresTestContainer
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
@QuarkusTestResource(PostgresTestContainer::class)
class CajuappControllerTest {

    @Test
    fun testFoodBalanceOk() {
        given()
            .`when`()
            .contentType("application/json")
            .body("{\"account\": \"123\",\"totalAmount\": 10.00,\"mcc\": \"5412\",\"merchant\": \"PADARIA DO ZE               SAO PAULO BR\"}")
            .post("/cajuapp/transaction")
            .then()
            .statusCode(200)
            .body(`is`("{\"code\":\"00\"}"))
    }

    @Test
    fun testCashBalanceOk() {
        given()
            .`when`()
            .contentType("application/json")
            .body("{\"account\": \"123\",\"totalAmount\": 100.00,\"mcc\": \"5812\",\"merchant\": \"PADARIA DO ZE               SAO PAULO BR\"}")
            .post("/cajuapp/transaction")
            .then()
            .statusCode(200)
            .body(`is`("{\"code\":\"00\"}"))
    }

    @Test
    fun testInvalidAccount() {
        given()
            .`when`()
            .contentType("application/json")
            .body("{\"account\": \"1234\",\"totalAmount\": 100.00,\"mcc\": \"5812\",\"merchant\": \"PADARIA DO ZE               SAO PAULO BR\"}")
            .post("/cajuapp/transaction")
            .then()
            .statusCode(200)
            .body(`is`("{\"code\":\"07\"}"))
    }

    @Test
    fun testWithoutBalance() {
        given()
            .`when`()
            .contentType("application/json")
            .body("{\"account\": \"123\",\"totalAmount\": 10000.00,\"mcc\": \"5812\",\"merchant\": \"PADARIA DO ZE               SAO PAULO BR\"}")
            .post("/cajuapp/transaction")
            .then()
            .statusCode(200)
            .body(`is`("{\"code\":\"51\"}"))
    }

    @Test
    fun testCashByNameOk() {
        given()
            .`when`()
            .contentType("application/json")
            .body("{\"account\": \"123\",\"totalAmount\": 100.00,\"mcc\": \"5812\",\"merchant\": \"UBER TRIP               SAO PAULO BR\"}")
            .post("/cajuapp/transaction")
            .then()
            .statusCode(200)
            .body(`is`("{\"code\":\"00\"}"))
    }
}