package uz.frankie.mahalla.model

data class NeighborhoodRes(val count: Int, val result: Neighborhoods)

data class Neighborhoods(val neighborhoods: List<Neighborhood>)
