$(function(){
	Backbone.emulateHTTP = true;
	Backbone.emulateJSON = true;
	
	window.app = {
		views : {},
		models : {},
		routers : {},
		collections : {},
		GlobalEvent : {}
	};
			
	window.template = function(id){
		var hash = '#';
		return _.template($(hash.concat(id)).html());
	};
	
	app.models.BookmarkModel = Backbone.Model.extend({
		defaults : {
			title : 'BookmarkModel',
			url : 'BookmarkUrl',
			tags : 'BookmarkTags'
		},
		initialize : function() {
			console.log('bookmark model is initialized');
		},
		isValid : function() {
			if ( !$.trim(title.value) || !$.trim(url.value) || !$.trim(tags.value)) {
                return false;
            };
			return true;
		},
		urlRoot : '../backbone_basics/api/Bookmark.php'
	});
	
	app.models.TagModel = Backbone.Model.extend({
		defaults : {
			name : 'TagName',
		}
	});
	
	app.collections.BookmarkListCollection = Backbone.Collection.extend({
		model : app.models.BookmarkModel,
		url : '../backbone_basics/api/BookmarkList.php'
	});

	app.collections.TagListCollection = Backbone.Collection.extend({
		model : app.models.TagModel,
		url : '../backbone_basics/api/TagList.php'
	});
	
	app.views.BookmarkView = Backbone.View.extend({
		tagName : 'li',
		template : template('bookmarkTemplate'),
		initialize : function() {
			console.log('BookmarkView is initialized');
		},
		events : {
			'click .btn-warning' : 'editBookmark',
			'click .btn-danger' : 'deleteBookmark'
		},
		editBookmark : function() {
			var existingModel = this.model;
			var newTitle = prompt('Please enter the new title', this.model.get('title'));
			if (!newTitle) {
				return;
			};
			var newUrl = prompt('Please enter the new url', this.model.get('url'));
			if (!newUrl) {
				return;
			};
			var newTags = prompt('Please enter the new tags', this.model.get('tags'));
			if (!newTags) {
				return;
			};
			this.model.set('title', newTitle);
			this.model.set('url', newUrl);
			this.model.set('tags', newTags);
			this.model.save();
			var updatedModel = this.model;
			Backbone.trigger('update', updatedModel);
		}, 
		deleteBookmark : function() {
			var bookmark = this.model;
			this.model.destroy();
			Backbone.trigger('deleteBookmark');
			Backbone.trigger('deleteTagCount', bookmark);
		},
		render: function() {
			this.$el.html(this.template(this.model.toJSON()));
			return this;
		}
	});
	
	app.views.BookmarkListView = Backbone.View.extend({
		el : $('#bookmarkList'),
		initialize : function() {
			Backbone.on('saveBookmark', this.addBookmark, this);
			Backbone.on('deleteBookmark', this.refresh, this);
			Backbone.on('filterBookmark', this.filterBookmark, this);
			Backbone.on('update', this.refresh, this);
			this.collection = new app.collections.BookmarkListCollection();
			this.render();
			console.log('BookmarkListView is initialized');
		},
		filterBookmark : function(tag) {
			$('#bookmarkTagFilter').text(tag);
			this.$el.empty();
			this.collection.each(function(bookmark){
				if(jQuery.inArray(tag, bookmark.get('tags')) == 0) {
					this.show(bookmark);
				}
			}, this);
			return this;
		},
		render : function(){
			this.collection.each(function(bookmark){
				this.show(bookmark);
			}, this);
			return this;
		},
		refresh : function() {
			this.$el.empty();
			this.render();
		},
		addBookmark : function(bookmark) {
			if(this.isNew(bookmark) && bookmark.isValid()) {
				this.collection.add(bookmark);
				this.show(bookmark);
				Backbone.trigger('addTagCount', bookmark);
			}
	    },
		show : function(bookmark) {
			var bookmarkView = new app.views.BookmarkView({ model: bookmark });
			this.$el.append(bookmarkView.render().el);
		},
		isNew : function(bookmark) {
			var isNew = true;
			this.collection.each(function(value){
				if (value.attributes.url == bookmark.attributes.url) {
					alert('there is already exists such url');
					isNew = false;
					return;
				};
			}, this);
			return isNew;
		}
	});
	
	app.models.TagCountModel = Backbone.Model.extend({
		defaults : {
			name : 'TagName',
			count : 1
		}
	});
		
	app.routers.BookmarkRouter = Backbone.Router.extend({
		routes : {
			'filter/:tag': 'bookmarkFilter'
		},
		initialize : function() {
			console.log('BookmarkRouter is initialized');
		},
		bookmarkFilter : function(tag) {
			Backbone.trigger('filterBookmark', tag);
		}
	});
		
	app.views.BookmarkListContainerView = Backbone.View.extend({ 
		el : $('#bookmarkListContainer'),
		inititalize : function() {
			this.bookmarkListView = new app.views.BookmarkListView({parent:this});
		},
		events : {
			'click #clearFilter' : 'clearFilter'
		},
		clearFilter : function() {
			$('#bookmarkTagFilter').text('none');
			Backbone.trigger('update');
		}
	});
	
	app.views.TagCountView = Backbone.View.extend({ 
		tagName : 'li',
		template : template('tagCountTemplate'),
		render : function( event ){
			this.$el.html(this.template(this.model.toJSON()));
			return this;
		}
	});

	app.views.TagCountListView = Backbone.View.extend({ 
		el : $('#tagCountList'),
		initialize : function() {
			Backbone.on('addTagCount', this.addTagCount, this);
			Backbone.on('deleteTagCount', this.deleteTagCount, this);
			this.collection = new app.collections.TagCountListCollection();
			this.render();
			console.log('TagCountListView is initialized');
		},
		render : function() {
			this.collection.each(function(tagCount){
				this.show(tagCount);
			}, this);
			return this;
		},
		refresh : function() {
			this.$el.empty();
			this.render();
		},
		deleteTagCount : function(bookmark) {
			var tags = bookmark.attributes.tags;
			var view = this;
			$(tags).each(function() {
				view.decrementTagCount(this);
			}, this);
			this.refresh();
		},
		addTagCount : function(bookmark) {
			var tags = bookmark.attributes.tags;
			var view = this;
			$(tags).each(function() {
				if (view.isNew(this)) {
					var tagCount = new app.models.TagCountModel({name : this});
					view.collection.add(tagCount);
					view.show(tagCount);
				} else {
					view.incrementTagCount(this);
					view.refresh();
				};
			}, this);
		},
		show : function(tagCount) {
			var tagCountView = new app.views.TagCountView({ model: tagCount });
			this.$el.append(tagCountView.render().el);
		},
		isNew : function(tagName) {
			var isNew = true;
			this.collection.each(function(value){
				if (value.attributes.name.toLowerCase() == tagName.toLowerCase()) {
					isNew = false;
					return;
				};
			}, this);
			return isNew;
		},
		incrementTagCount : function(tagName) {
			this.collection.each(function(value){
				if (value.attributes.name.toLowerCase() == tagName.toLowerCase()) {
					value.attributes.count++;
					return;
				};
			}, this);
		},
		decrementTagCount : function(tagName) {
			this.collection.each(function(value){
				if (value.attributes.name.toLowerCase() == tagName.toLowerCase()) {
					value.attributes.count--;
					if(value.attributes.count == 0) {
						value.destroy();
					}
					return;
				};
			}, this);
		}
	});

	app.views.BookmarkFormView = Backbone.View.extend({ 
		el : $('#form'),
		initialize : function() {
			this.bookmarkListView = new app.views.BookmarkListView();
			this.tagCountListView = new app.views.TagCountListView();
			this.bookmarkListContainerView = new app.views.BookmarkListContainerView();
			console.log('BookmarkFormView is initialized');
		},
		events: {
			'click #btnSave'  : 'saveBookmark',
			'click #btnClear' : 'clearForm'
		},
		saveBookmark : function(e) {
			e.preventDefault();
			var newBookmarkTitle = $('#title').val();
			var newBookmarkUrl = $('#url').val();
			var newBookmarkTags = $('#tags').val().split(',');
			var bookmark = new app.models.BookmarkModel({
				title : newBookmarkTitle,
				url : newBookmarkUrl,
				tags : newBookmarkTags
			});
			Backbone.trigger('saveBookmark', bookmark);
		},
		clearForm : function() {
			document.getElementById("form").reset();
		}
	});

	app.collections.TagCountListCollection = Backbone.Collection.extend({
		model : app.models.TagCountModel,
		url : '../api/TagCountList.php'
	});
	
	var bookmarkFormView = new app.views.BookmarkFormView();
	var bookmarkRouter = new app.routers.BookmarkRouter();
	Backbone.history.start({pushState: true});
	$(document).on('click', '.filter', function(e){
		e.preventDefault();
		Backbone.trigger('filterBookmark', $(this).attr('href'));
	});
});