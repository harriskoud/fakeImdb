import { MovieService } from './../../services/movie.service';
import { Component, OnInit, Input } from '@angular/core';
import { Movie } from 'src/app/model/Movie';
import { RelationShipUserMovies } from 'src/app/model/RelationShipUserMovie';

@Component({
  selector: 'movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css']
})
export class MovieComponent implements OnInit {


  @Input() movie: Movie;
  favour: RelationShipUserMovies = new RelationShipUserMovies();
  constructor(private movieService: MovieService) { }

  ngOnInit() {
    console.log("testttttttt")
    this.getFavour()
  }

  like(like:string){
    this.movieService.likeMovie(like,this.movie).subscribe(response =>{
      this.movie = response
        console.log(response)
        this.getFavour()
    })
  }

  getFavour(){
    this.movieService.getFavourOfUserForMovie(this.movie).subscribe(response =>{
      this.favour = response
    })
  }

}
