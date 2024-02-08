package com.example.myapplication.data.models.movieDetail.mapper

import com.example.myapplication.data.models.movieDetail.CountryDetailTDO
import com.example.myapplication.domain.models.Country

class CountryDetailMapper {
    fun mapFromCountryDetailTDO(countryDetailTDO: CountryDetailTDO) : Country {
        return Country(
            name = countryDetailTDO.name
        )
    }
}

