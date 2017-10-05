export class AddNewSchedule { 
    constructor ( 
       public idCourse: number,
       public idRoom: number,
       public idTraining: number,
       public idMainTrainer: number, 
       public idBackupTrainer: number,
       public startDate: Date,
       public endDate: Date,
       public capacity: string,
       public periodic: boolean,
       public day: number,
       public hour: string,
       public createdBy: string,
       public updatedBy: string
    ) {  } 
 }