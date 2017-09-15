import { Component, Inject } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { LoginComponent } from './login.component';
import { NavbarComponent } from '../components/navbar/navbar.component';

@Component({
    selector: 'tas-role',
    templateUrl: './role.component.html',
    styleUrls: ['./role.component.css']
  })
export class RoleComponent{
    constructor(
    public dialogRef: MdDialogRef<LoginComponent>,
    public dialogRef2: MdDialogRef<NavbarComponent>,
    @Inject(MD_DIALOG_DATA) public data: any, private router: Router) { }

    toDashboard(): void {
        this.router.navigate(['/home/dashboard']);
        this.dialogRef.close();
    }
}