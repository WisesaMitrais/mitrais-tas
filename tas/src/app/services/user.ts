export class User { 
    constructor ( 
       public idUser: number,  
       public name: string,
       public email: string,
       public username: string,
       public password: string,
       public active: number,
       public jobFamilyStream: number,
       public grade: string,
       public office: object 
    ) {  } 
 }