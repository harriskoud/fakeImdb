import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/app/model/Movie';
import { MovieService } from 'src/app/services/movie.service';

@Component({
  selector: 'app-movies-dashboard',
  templateUrl: './movies-dashboard.component.html',
  styleUrls: ['./movies-dashboard.component.css']
})
export class MoviesDashboardComponent implements OnInit {

  movieList = Array<Movie>();
  movie: Movie
  message: string


  constructor(private movieService: MovieService, private authService:AuthService) { }

  ngOnInit() {
    this.getAllMovies()
  }

  logout(){
    this.authService.logOut()
  }

  addMovie(){
    this.movieService.addMovie(this.movie).subscribe(response =>{
      this.getAllMovies()
    },err=>{
      this.message = "The movie wasn't added"
    })
  }

  getAllMovies(){
    this.movieService.getAllMovies().subscribe(response =>{
      console.log(response)
      response.forEach(movie => this.movieList.push(movie))
    })
  }

}
