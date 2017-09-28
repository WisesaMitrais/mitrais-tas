export class Period { 
    constructor ( 
       public idTraining: number,  
       public name: string,
       public active: boolean,
       public startDate: string,
       public endDate: string,
       public createdBy: boolean,
       public updatedBy: string,
       public courses: number, 
       public bccTraining: boolean
    ) {  } 
 }