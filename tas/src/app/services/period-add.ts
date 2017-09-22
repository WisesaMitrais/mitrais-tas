export class AddPeriod { 
    constructor ( 
       public trainingName: string,
       public startDate: string,
       public endDate: string,
       public openEnrollment: boolean,
       public bccTraining: boolean,
       public createdBy: number,
       public updatedBy: number,
       public active: boolean
    ) {  } 
 }