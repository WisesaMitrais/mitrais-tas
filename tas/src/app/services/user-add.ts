export class AddUser { 
    constructor (
       public name: string,
       public username: String,
       public password: String,
       public email: string,
       public jobFamilyStream: string,
       public grade: string,
       public idOffice: number,
       public active: boolean,
       public roles: Array<String>
    ) {  } 
 }