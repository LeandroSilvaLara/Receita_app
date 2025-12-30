package com.courselara.receitafacil.core.domain.exceptions

import com.courselara.receitafacil.core.data.remote.responses.ErrorResponse
import java.lang.RuntimeException

class ErrorResponseException (val error: ErrorResponse): RuntimeException()