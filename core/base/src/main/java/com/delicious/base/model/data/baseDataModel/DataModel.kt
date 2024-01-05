package com.delicious.base.model.data.baseDataModel

import com.delicious.base.model.domain.baseDomainModel.DomainModel

interface DataModel {
    fun toDomainModel(): DomainModel
}
