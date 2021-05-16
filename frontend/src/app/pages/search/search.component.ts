// app.component.ts

import { Component } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { AlbumService } from "../../services/album.service";
import { Album } from "../../models/Album";
import { Observable } from "rxjs";

@Component({
  selector: 'search-root',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
    angForm = new FormGroup({
        name: new FormControl('', Validators.required),
    });

      constructor(private albumService: AlbumService) {
      }
    albums = [];

    onSubmit(): void {
        const keyword = this.angForm.get('name').value;
        this.albumService.search(keyword).subscribe(albums => { this.albums = albums; });
    }
}
