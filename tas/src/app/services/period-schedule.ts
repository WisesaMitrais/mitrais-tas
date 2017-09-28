export class SchedulePeriod { 
    constructor ( 
       public idSchedule: string,  
       public name: string,
       public mainTrainer: string,
       public backupTrainer: string,
       public room: string,
       public day: string,
       public startTime: string,
       public endTime: string,
       public capacity: string,
       public participantNumber: string,
       public scheduleType: string,
       public periodic: boolean,
       public createdBy: string,
       public createdAt: string,
       public updatedBy: string,
       public updatedAt: string,
       public city: string
    ) {  } 
 }