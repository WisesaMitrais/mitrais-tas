import { Injectable } from '@angular/core';

declare var $: any;

@Injectable() 
export class NotificationService {

    constructor() { }

    public setNotificationInfo(message: string){
        $.notify({
            icon: "notifications",
            message: "<b>Information</b> - "+message
        },{
            type: 'info',
            timer: 3000,
            placement: {
                from: 'bottom',
                align: 'center'
            }
        });
    }

    public setNotificationWarning(message: string){
        $.notify({
            icon: "notifications",
            message: "<b>WARNING</b> - "+message
        },{
            type: 'warning',
            timer: 3000,
            placement: {
                from: 'bottom',
                align: 'center'
            }
        });
    }

    public setNotificationError(message: string){
        $.notify({
            icon: "notifications",
            message: "<b>Error</b> - "+message
        },{
            type: 'danger',
            timer: 3000,
            placement: {
                from: 'bottom',
                align: 'center'
            }
        });
    }
 }