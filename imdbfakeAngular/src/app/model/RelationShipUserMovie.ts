import { Movie } from './Movie';
export class RelationShipUserMovies {
    userFavour: UserFavour
    movie: Movie
    
}

interface UserFavour{
    movieId: number,
    userId: number,
    favour: number
}