import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root',
})
export class AlertService {

  constructor(private toastr: ToastrService) { }

  showError(message: string): void {
    this.toastr.error(message, 'Error', {
      timeOut: 3000,
      positionClass: 'toast-bottom-center',
      toastClass: 'custom-toast-error',
      closeButton: true,
      enableHtml: true,
      tapToDismiss: true,
    });
  }

  showSuccess(message: string): void {
    this.toastr.success(message, 'Éxito', {
      timeOut: 3000,
      positionClass: 'toast-bottom-center',
      toastClass: 'custom-toast-success',
      closeButton: true,
      enableHtml: true,
      tapToDismiss: true,
    });
  }
}
