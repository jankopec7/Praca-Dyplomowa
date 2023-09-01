import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtService } from '../common/service/jwt.service';
import { ProfileService } from './profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  constructor(private profileService: ProfileService,
    private jwtService: JwtService,
    private router: Router
    ) { }

    ngOnInit(): void {
      if(!this.jwtService.isLoggedIn()) {
        this.router.navigate(["/login"]);
      }
    }
}
