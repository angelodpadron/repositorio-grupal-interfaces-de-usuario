package org.unqflixabm.appModels

import data.getUNQFlix
import domain.ExistsException
import domain.Serie
import domain.UNQFlix
import org.unqflixabm.exceptions.NonSelectException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable

class UNQflixAppModel {

    private var system: UNQFlix = getUNQFlix()
    var series: MutableList<SeriesAppModel> = initSeries()
    var categories: MutableList<CategoryAppModel> = initCategories()
    var selectSerie: SeriesAppModel? = null // selecciona serie aunque parezca que seleccione el id


    fun initSeries(): MutableList<SeriesAppModel> {
        return system.series.map { SeriesAppModel(it) }.toMutableList()
    }

    fun initCategories(): MutableList<CategoryAppModel> {
        return system.categories.map { CategoryAppModel(it) }.toMutableList()
    }
    fun catchNonSelectSerieException(selectSerie: SeriesAppModel?){
        try{
            this.nonSelectSerieException(selectSerie)
        }
        catch(e: NonSelectException){
            throw UserException(e.message)
        }
    }
    fun nonSelectSerieException(selectSerie: SeriesAppModel?){
        if (selectSerie == null) {
            throw NonSelectException("Please select a serie before continue")
        }
    }

    fun catchExistSerieException(serie :SeriesAppModel){

        var unqflix : UNQflixAppModel = this

        try{
           unqflix.addSerie(serie)
        }
        catch( e : ExistsException){
            UserException(e.message)
        }
    }

    //ALTA
    fun addSerie(seriesAppModel: SeriesAppModel){
        //agregar al modelo
        system.addSerie(seriesAppModel.model())
        //update viewmodel
        this.initSeries()
    }

    //BAJA
    fun deleteSerie(selectSerie:SeriesAppModel?){
        if (selectSerie != null) {
            system.deleteSerie(selectSerie.id)
        }
        series = initSeries()
    }

    //MODIFICACION

}