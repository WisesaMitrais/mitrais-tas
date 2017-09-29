export class ShowEnrollParticipant { 
    constructor ( 
        public idEnrollment: number,
        public periodName: string,
        public courseName: string,
        public trainer: string,
        public startTime: string,
        public endTime: string,
        public status: string,
        public userName: string,
        public userNumber: number
    ) {  } 
 }