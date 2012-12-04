var tagHandlerMessage = "An error occurred:";

function tagErrorHandler(message, exception) {
alert(tagHandlerMessage + ' Error Code: ' + message + ", exception = " + exception);
};


function tagErrorHandler2(message, messageId_for_tags) {
if (messageId_for_tags != null) {
$('tag-error-text-' + messageId_for_tags).innerHTML = message;
Effect.Appear($('tag-error-table-' + messageId_for_tags));
Effect.Fade($('tag-error-table-' + messageId_for_tags),{delay: 10});
}
};

function validateTags(messageId_for_tags) {
TagValidator.isValid($('tags-' + messageId_for_tags).value,
{
callback: function(isValid) {
if (isValid) {
$('tagForm-' + messageId_for_tags).submit();
}
else {
tagErrorHandler2(tagHandlerMessage, messageId_for_tags);
}
},
timeout: 20000, // 20 seconds
errorHandler: tagErrorHandler
}
);
}
