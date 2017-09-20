export class User { 
    constructor ( 
       public idUser: string,  
       public name: string,
       public email: string,
       public jobFamilyStream: string,
       public accountName: string,
       public active: boolean,
       public role: string,
       public grade: string 
    ) {  } 
 }