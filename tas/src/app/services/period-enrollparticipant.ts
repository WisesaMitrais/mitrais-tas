export class AddEnrollParticipant { 
    constructor ( 
        public idSchedule: number,
        public idUser: Array<string>
    ) {  } 
 }