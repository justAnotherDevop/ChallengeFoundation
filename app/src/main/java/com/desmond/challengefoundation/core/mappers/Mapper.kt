package com.desmond.challengefoundation.core.mappers

interface Mapper<IN : Model, OUT : Model> {
    fun map(input: IN): OUT
}

interface Model
interface PresentationModel : Model
interface DomainModel : Model
interface DataModel : Model