import { MovieService } from './../../services/movie.service';
import { Component, OnInit, Input } from '@angular/core';
import { Movie } from 'src/app/model/Movie';

@Component({
  selector: 'movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css']
})
export class MovieComponent implements OnInit {


  @Input() movie: Movie;
  constructor(private movieService: MovieService) { }

  ngOnInit() {
  }

  like(like:string){
    console.log("mesaa")
    this.movieService.likeMovie(like,this.movie).subscribe(response =>{
      this.movie = response
        console.log(response)
    })
  }

}
