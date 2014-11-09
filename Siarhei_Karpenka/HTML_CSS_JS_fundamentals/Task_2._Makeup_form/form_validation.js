function validate()
{ 
   if( document.DeliveryRegistration.fullname.value.length < 3 ) {
     alert( "The field name can not be less than 3 characters!" );
     document.DeliveryRegistration.fullname.focus() ;
     return false;
   }
   return( true );
}