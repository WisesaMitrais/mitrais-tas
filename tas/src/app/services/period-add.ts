export class AddPeriod { 
    constructor ( 
       public trainingName: string,
       public startDate: Date,
       public endDate: Date,
       public openEnrollment: boolean,
       public bccTraining: boolean,
       public createdBy: number,
       public updatedBy: number,
       public active: boolean
    ) {  } 
 }