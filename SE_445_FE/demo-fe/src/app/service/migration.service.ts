import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AngularFirestore} from "@angular/fire/firestore";
import {finalize} from "rxjs/operators";
import {FileUpload} from "../models/FileUpload";
import {AngularFireStorage} from "@angular/fire/storage";

const BASE_PATH = "http://localhost:8080/api";

@Injectable({
  providedIn: 'root'
})
export class MigrationService {

  /* Tsv to Maria */
  tsvToMaria(file: any): Observable<any> {
    return this.httpClient.post<any>(`${BASE_PATH}/tsv-to-maria/${file}` , file);
  }

  /* Push file to storage and show percentage */
  pushFileToStorage(fileUpload: FileUpload): Observable<any> {
    const filePath = `uploads/${fileUpload.file.name}`;
    const storageRef = this.storage.ref(filePath);
    const uploadTask = this.storage.upload(filePath, fileUpload.file);

    uploadTask.snapshotChanges().pipe(
      finalize(() => {
        storageRef.getDownloadURL().subscribe(downloadURL => {
          fileUpload.url = downloadURL;
          fileUpload.name = fileUpload.file.name;
        });
      })
    ).subscribe();
    return uploadTask.percentageChanges();
  }


  constructor(private httpClient: HttpClient, private storage: AngularFireStorage) {
  }
}
