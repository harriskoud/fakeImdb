import { Movie } from './../model/Movie';
import { HttpHeaders, HttpClient, HttpParams, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable, NgModule } from '@angular/core';
import { AuthInterceptor } from '../model/AuthInterseptor';
import { RelationShipUserMovies } from '../model/RelationShipUserMovie';

@Injectable({
  providedIn: 'root'
})

@NgModule({
  imports: [],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ]
})
export class MovieService {

  constructor(private httpClient: HttpClient) { }

  getAllMovies() {
    return this.httpClient.get<Array<Movie>>("http://localhost:8090/movie", {
      headers: new HttpHeaders({
        "Authorization": "Bearer " + localStorage.getItem('token')
      })
    })
  }

  addMovie(movie: Movie) {
    return this.httpClient.post<Movie>("http://localhost:8090/movie", movie)
  }

  likeMovie(like: string, movie: Movie) {
    let params = new HttpParams()
    params.set("like", like)
    let userId = localStorage.getItem("userId")

    return this.httpClient.get<Movie>(
      "http://localhost:8090/movie/like/" + movie.movieId + "?like=" + like+"&userId="+userId,
      {
        headers: new HttpHeaders({
          "Authorization": "Bearer " + localStorage.getItem('token')
        })
      }
    )
  }

  getFavourOfUserForMovie(movie:Movie){
    let userId = localStorage.getItem("userId")

    return this.httpClient.get<RelationShipUserMovies>(
      "http://localhost:8090/movie/" + movie.movieId + "/user/" + userId,
      {
        headers: new HttpHeaders({
          "Authorization": "Bearer " + localStorage.getItem('token')
        })
      }
    )

  }

  createTokenBearerAuthHeader() {
    var headers_object = new HttpHeaders();
    headers_object.append("Authorization", "Bearer " + localStorage.getItem('token'));

    return headers_object;
  }

}
